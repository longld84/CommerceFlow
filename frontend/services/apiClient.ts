const BASE_URL = process.env.NEXT_PUBLIC_API_URL || "http://localhost:8080";

interface RequestOptions extends RequestInit {
  requiresAuth?: boolean;
}

export async function apiClient<T>(
  endpoint: string,
  options: RequestOptions = {}
): Promise<T> {
  const { requiresAuth = true, headers = {}, ...rest } = options;

  const defaultHeaders: Record<string, string> = {
    "Content-Type": "application/json",
  };

  // Attach token if running in browser and auth is required
  if (requiresAuth && typeof window !== "undefined") {
    const token = localStorage.getItem("access_token");
    if (token) {
      defaultHeaders["Authorization"] = `Bearer ${token}`;
    }
  }

  const response = await fetch(`${BASE_URL}${endpoint}`, {
    headers: {
      ...defaultHeaders,
      ...headers,
    },
    ...rest,
  });

  // Handle 401 Unauthorized / Token Expiry
  if (response.status === 401 && typeof window !== "undefined") {
    // Luồng refresh token sẽ được hoàn thiện khi kết nối API
    console.warn("Token expired or unauthorized. Please re-login.");
  }

  if (!response.ok) {
    const errorData = await response.json().catch(() => null);
    throw new Error(
      errorData?.message || `API Error: ${response.status} ${response.statusText}`
    );
  }

  return response.json() as Promise<T>;
}


import { apiClient } from "./apiClient";
import { API_ENDPOINTS } from "@/constants/apiEndpoints";
import { ApiResponse } from "@/types/api";
import { AuthResponse, LoginRequest, RegisterRequest } from "@/types/auth";
import { User } from "@/types/user";

export const authService = {
  login: async (credentials: LoginRequest): Promise<ApiResponse<AuthResponse>> => {
    return apiClient<ApiResponse<AuthResponse>>(API_ENDPOINTS.AUTH.LOGIN, {
      method: "POST",
      body: JSON.stringify(credentials),
      requiresAuth: false,
    });
  },

  register: async (data: RegisterRequest): Promise<ApiResponse<User>> => {
    return apiClient<ApiResponse<User>>(API_ENDPOINTS.AUTH.REGISTER, {
      method: "POST",
      body: JSON.stringify(data),
      requiresAuth: false,
    });
  },

  logout: async (): Promise<ApiResponse<void>> => {
    return apiClient<ApiResponse<void>>(API_ENDPOINTS.AUTH.LOGOUT, {
      method: "POST",
    });
  },

  getMe: async (): Promise<ApiResponse<User>> => {
    return apiClient<ApiResponse<User>>(API_ENDPOINTS.AUTH.ME, {
      method: "GET",
    });
  },
};


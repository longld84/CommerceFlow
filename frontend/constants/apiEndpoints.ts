export const API_ENDPOINTS = {
  AUTH: {
    LOGIN: "/api/v1/auth/login",
    REGISTER: "/api/v1/auth/register",
    REFRESH_TOKEN: "/api/v1/auth/refresh-token",
    LOGOUT: "/api/v1/auth/logout",
    GOOGLE_LOGIN: "/api/v1/auth/google",
    ME: "/api/v1/users/me",
  },
  USER: {
    PROFILE: "/api/v1/users/profile",
    ADDRESSES: "/api/v1/users/addresses",
  },
  CATEGORY: {
    BASE: "/api/v1/categories",
  },
  PRODUCT: {
    BASE: "/api/v1/products",
  },
  CART: {
    BASE: "/api/v1/carts",
  },
  ORDER: {
    BASE: "/api/v1/orders",
  },
} as const;


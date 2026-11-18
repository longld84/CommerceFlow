import { apiClient } from "./apiClient";
import { API_ENDPOINTS } from "@/constants/apiEndpoints";
import { ApiResponse } from "@/types/api";
import { User, UserAddress } from "@/types/user";

export const userService = {
  getProfile: async (): Promise<ApiResponse<User>> => {
    return apiClient<ApiResponse<User>>(API_ENDPOINTS.USER.PROFILE, {
      method: "GET",
    });
  },

  updateProfile: async (data: Partial<User>): Promise<ApiResponse<User>> => {
    return apiClient<ApiResponse<User>>(API_ENDPOINTS.USER.PROFILE, {
      method: "PUT",
      body: JSON.stringify(data),
    });
  },

  getAddresses: async (): Promise<ApiResponse<UserAddress[]>> => {
    return apiClient<ApiResponse<UserAddress[]>>(API_ENDPOINTS.USER.ADDRESSES, {
      method: "GET",
    });
  },

  addAddress: async (
    data: Omit<UserAddress, "id">
  ): Promise<ApiResponse<UserAddress>> => {
    return apiClient<ApiResponse<UserAddress>>(API_ENDPOINTS.USER.ADDRESSES, {
      method: "POST",
      body: JSON.stringify(data),
    });
  },
};


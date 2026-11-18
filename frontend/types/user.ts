export type Role = "ROLE_USER" | "ROLE_ADMIN";

export interface User {
  id: number;
  email: string;
  fullName: string;
  phone?: string;
  avatarUrl?: string;
  role: Role;
  createdAt: string;
  updatedAt: string;
}

export interface UserAddress {
  id: number;
  recipientName: string;
  phoneNumber: string;
  provinceCity: string;
  district: string;
  ward: string;
  detailedAddress: string;
  isDefault: boolean;
}


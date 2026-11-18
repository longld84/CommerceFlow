"use client";

import { useAuth } from "@/hooks/useAuth";

export default function ProfilePage() {
  const { user, loading } = useAuth();

  if (loading) {
    return (
      <div className="container mx-auto py-12 px-4 text-center">
        <p className="text-zinc-500">Đang tải thông tin cá nhân...</p>
      </div>
    );
  }

  return (
    <div className="container mx-auto max-w-4xl py-12 px-4 sm:px-6">
      <h1 className="text-2xl font-bold tracking-tight text-zinc-900 mb-6">
        Hồ sơ cá nhân & Sổ địa chỉ
      </h1>
      <div className="grid grid-cols-1 md:grid-cols-3 gap-6">
        <div className="bg-white border rounded-xl p-6 shadow-sm">
          <h2 className="font-semibold text-lg text-zinc-900 mb-4">Thông tin tài khoản</h2>
          <p className="text-sm text-zinc-600 mb-2">
            <strong>Họ tên:</strong> {user?.fullName || "Chưa cập nhật"}
          </p>
          <p className="text-sm text-zinc-600 mb-2">
            <strong>Email:</strong> {user?.email || "Chưa cập nhật"}
          </p>
          <p className="text-sm text-zinc-600">
            <strong>Số điện thoại:</strong> {user?.phone || "Chưa cập nhật"}
          </p>
        </div>

        <div className="md:col-span-2 bg-white border rounded-xl p-6 shadow-sm">
          <div className="flex justify-between items-center mb-4">
            <h2 className="font-semibold text-lg text-zinc-900">Sổ địa chỉ nhận hàng</h2>
            <button className="text-xs bg-zinc-900 text-white px-3 py-1.5 rounded-lg hover:bg-zinc-800">
              + Thêm địa chỉ mới
            </button>
          </div>
          <p className="text-sm text-zinc-500 italic">Chưa có địa chỉ giao hàng nào được lưu.</p>
        </div>
      </div>
    </div>
  );
}


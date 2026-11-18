"use client";

import Link from "next/link";
import { ROUTES } from "@/constants/routes";

export default function LoginPage() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-xl font-bold tracking-tight text-zinc-900">Đăng nhập</h2>
        <p className="mt-1 text-xs text-zinc-500">
          Chào mừng bạn quay lại với CommerceFlow
        </p>
      </div>

      <form className="space-y-4" onSubmit={(e) => e.preventDefault()}>
        <div>
          <label className="block text-xs font-medium text-zinc-700">Email</label>
          <input
            type="email"
            placeholder="example@domain.com"
            className="mt-1 w-full rounded-lg border border-zinc-300 px-3 py-2 text-sm focus:border-zinc-900 focus:outline-none"
          />
        </div>

        <div>
          <label className="block text-xs font-medium text-zinc-700">Mật khẩu</label>
          <input
            type="password"
            placeholder="••••••••"
            className="mt-1 w-full rounded-lg border border-zinc-300 px-3 py-2 text-sm focus:border-zinc-900 focus:outline-none"
          />
        </div>

        <button
          type="submit"
          className="w-full rounded-lg bg-zinc-900 py-2.5 text-sm font-semibold text-white hover:bg-zinc-800 transition"
        >
          Đăng nhập
        </button>
      </form>

      <div className="relative my-4">
        <div className="absolute inset-0 flex items-center">
          <div className="w-full border-t border-zinc-200" />
        </div>
        <div className="relative flex justify-center text-xs">
          <span className="bg-white px-2 text-zinc-500">Hoặc tiếp tục với</span>
        </div>
      </div>

      <button
        type="button"
        className="w-full flex items-center justify-center gap-2 rounded-lg border border-zinc-300 py-2 text-sm font-medium text-zinc-700 hover:bg-zinc-50 transition"
      >
        <span>Google</span>
      </button>

      <p className="text-center text-xs text-zinc-600">
        Chưa có tài khoản?{" "}
        <Link href={ROUTES.AUTH.REGISTER} className="font-semibold text-zinc-900 hover:underline">
          Đăng ký ngay
        </Link>
      </p>
    </div>
  );
}


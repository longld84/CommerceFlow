"use client";

import Link from "next/link";
import { ROUTES } from "@/constants/routes";

export default function RegisterPage() {
  return (
    <div className="space-y-6">
      <div>
        <h2 className="text-xl font-bold tracking-tight text-zinc-900">Tạo tài khoản mới</h2>
        <p className="mt-1 text-xs text-zinc-500">
          Trải nghiệm mua sắm nội thất & decor tối giản
        </p>
      </div>

      <form className="space-y-4" onSubmit={(e) => e.preventDefault()}>
        <div>
          <label className="block text-xs font-medium text-zinc-700">Họ và tên</label>
          <input
            type="text"
            placeholder="Nguyễn Văn A"
            className="mt-1 w-full rounded-lg border border-zinc-300 px-3 py-2 text-sm focus:border-zinc-900 focus:outline-none"
          />
        </div>

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
            placeholder="Tối thiểu 6 ký tự"
            className="mt-1 w-full rounded-lg border border-zinc-300 px-3 py-2 text-sm focus:border-zinc-900 focus:outline-none"
          />
        </div>

        <button
          type="submit"
          className="w-full rounded-lg bg-zinc-900 py-2.5 text-sm font-semibold text-white hover:bg-zinc-800 transition"
        >
          Đăng ký tài khoản
        </button>
      </form>

      <p className="text-center text-xs text-zinc-600">
        Đã có tài khoản?{" "}
        <Link href={ROUTES.AUTH.LOGIN} className="font-semibold text-zinc-900 hover:underline">
          Đăng nhập
        </Link>
      </p>
    </div>
  );
}


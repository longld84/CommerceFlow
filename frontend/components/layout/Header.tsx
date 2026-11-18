import Link from "next/link";
import { ROUTES } from "@/constants/routes";

export default function Header() {
  return (
    <header className="sticky top-0 z-40 w-full border-b bg-white/95 backdrop-blur">
      <div className="container mx-auto flex h-16 items-center justify-between px-4 sm:px-6">
        <Link href={ROUTES.HOME} className="text-xl font-bold tracking-tight text-zinc-900">
          CommerceFlow
        </Link>
        <nav className="flex items-center gap-6 text-sm font-medium">
          <Link href={ROUTES.HOME} className="hover:text-zinc-600 transition-colors">
            Trang chủ
          </Link>
          <Link href={ROUTES.SHOP} className="hover:text-zinc-600 transition-colors">
            Cửa hàng
          </Link>
        </nav>
        <div className="flex items-center gap-4">
          <Link
            href={ROUTES.AUTH.LOGIN}
            className="text-sm font-medium text-zinc-700 hover:text-zinc-950"
          >
            Đăng nhập
          </Link>
          <Link
            href={ROUTES.AUTH.REGISTER}
            className="rounded-full bg-zinc-900 px-4 py-2 text-xs font-semibold text-white hover:bg-zinc-800 transition"
          >
            Đăng ký
          </Link>
        </div>
      </div>
    </header>
  );
}


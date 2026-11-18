import Link from "next/link";
import { ROUTES } from "@/constants/routes";

export default function AdminLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="min-h-screen flex bg-zinc-100">
      <aside className="w-64 bg-zinc-900 text-zinc-100 flex flex-col p-4">
        <div className="text-lg font-bold py-2 border-b border-zinc-800">
          Admin Portal
        </div>
        <nav className="mt-6 flex flex-col gap-2 text-sm">
          <Link href={ROUTES.ADMIN.DASHBOARD} className="px-3 py-2 rounded-lg bg-zinc-800">
            Tổng quan
          </Link>
          <Link href={ROUTES.ADMIN.PRODUCTS} className="px-3 py-2 rounded-lg hover:bg-zinc-800">
            Sản phẩm & Biến thể
          </Link>
          <Link href={ROUTES.ADMIN.ORDERS} className="px-3 py-2 rounded-lg hover:bg-zinc-800">
            Đơn hàng
          </Link>
          <Link href={ROUTES.HOME} className="mt-auto px-3 py-2 text-xs text-zinc-400 hover:text-white">
            ← Quay về cửa hàng
          </Link>
        </nav>
      </aside>
      <main className="flex-1 p-8">{children}</main>
    </div>
  );
}


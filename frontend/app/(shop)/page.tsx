import Link from "next/link";
import { ROUTES } from "@/constants/routes";

export default function HomePage() {
  return (
    <div className="flex flex-col items-center justify-center py-20 px-4 text-center">
      <span className="rounded-full bg-zinc-100 px-3 py-1 text-xs font-semibold text-zinc-700 mb-4">
        Nội Thất & Decor Hiện Đại
      </span>
      <h1 className="text-4xl sm:text-6xl font-bold tracking-tight text-zinc-900 max-w-2xl">
        Kiến tạo không gian sống tối giản cùng CommerceFlow
      </h1>
      <p className="mt-4 text-base text-zinc-600 max-w-lg">
        Khám phá bộ sưu tập decor phong cách Nordic, Minimalist, Vintage tinh tế và sang trọng.
      </p>
      <div className="mt-8 flex gap-4">
        <Link
          href={ROUTES.SHOP}
          className="rounded-full bg-zinc-900 px-6 py-3 text-sm font-semibold text-white hover:bg-zinc-800 transition"
        >
          Khám phá ngay
        </Link>
      </div>
    </div>
  );
}


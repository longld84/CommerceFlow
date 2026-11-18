import Link from "next/link";
import { ROUTES } from "@/constants/routes";

export default function AuthLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <div className="min-h-screen flex flex-col justify-center py-12 px-4 sm:px-6 lg:px-8 bg-zinc-50">
      <div className="sm:mx-auto sm:w-full sm:max-w-md text-center mb-6">
        <Link href={ROUTES.HOME} className="text-2xl font-bold tracking-tight text-zinc-900">
          CommerceFlow
        </Link>
      </div>
      <div className="sm:mx-auto sm:w-full sm:max-w-md bg-white p-8 border rounded-2xl shadow-sm">
        {children}
      </div>
    </div>
  );
}


export default function AdminDashboardPage() {
  return (
    <div className="space-y-6">
      <h1 className="text-2xl font-bold tracking-tight text-zinc-900">
        Bảng điều khiển Quản trị (Admin Dashboard)
      </h1>
      <div className="grid grid-cols-1 sm:grid-cols-3 gap-6">
        <div className="p-6 bg-white rounded-xl border shadow-sm">
          <p className="text-xs text-zinc-500 font-medium">Doanh thu hôm nay</p>
          <p className="text-2xl font-bold text-zinc-900 mt-2">0 ₫</p>
        </div>
        <div className="p-6 bg-white rounded-xl border shadow-sm">
          <p className="text-xs text-zinc-500 font-medium">Đơn hàng mới</p>
          <p className="text-2xl font-bold text-zinc-900 mt-2">0</p>
        </div>
        <div className="p-6 bg-white rounded-xl border shadow-sm">
          <p className="text-xs text-zinc-500 font-medium">Sản phẩm sắp hết hàng</p>
          <p className="text-2xl font-bold text-zinc-900 mt-2">0</p>
        </div>
      </div>
    </div>
  );
}


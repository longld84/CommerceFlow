export const ROUTES = {
  HOME: "/",
  SHOP: "/shop",
  CART: "/cart",
  CHECKOUT: "/checkout",
  AUTH: {
    LOGIN: "/login",
    REGISTER: "/register",
    FORGOT_PASSWORD: "/forgot-password",
  },
  USER: {
    PROFILE: "/profile",
    ORDERS: "/orders",
    WISHLIST: "/wishlist",
  },
  ADMIN: {
    DASHBOARD: "/admin",
    PRODUCTS: "/admin/products",
    ORDERS: "/admin/orders",
    CATEGORIES: "/admin/categories",
  },
} as const;


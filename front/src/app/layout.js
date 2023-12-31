import QueryProvider from "@/hooks/QueryClient";
import GlobalStyles from "@/styles/global";

export default function RootLayout({ children }) {
  return (
    <html>
      <body suppressHydrationWarning={true}>
        <GlobalStyles />
        <QueryProvider>{children}</QueryProvider>
      </body>
    </html>
  );
}

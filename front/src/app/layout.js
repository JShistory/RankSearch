import GlobalStyles from "@/styles/global";

export default function RootLayout({ children }) {
  return (
    <html>
      <body suppressHydrationWarning={true}>
        <GlobalStyles />
        {children}
      </body>
    </html>
  );
}

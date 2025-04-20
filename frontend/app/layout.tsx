import type { Metadata } from "next";
import { Geist, Geist_Mono } from "next/font/google";
import Link from 'next/link';
import "./globals.css";

const geistSans = Geist({
  variable: "--font-geist-sans",
  subsets: ["latin"],
});

const geistMono = Geist_Mono({
  variable: "--font-geist-mono",
  subsets: ["latin"],
});

export const metadata: Metadata = {
  title: "Re-Bank",
  description: "Re-Bank - The Real Bank, application written with java spring and nextjs allowing users to manage their accounts, transactions, assets, and notifications.",
};

export default function RootLayout({
  children,
}: Readonly<{
  children: React.ReactNode;
}>) {
  return (
    <html lang="en">
      <body
        className={`${geistSans.variable} ${geistMono.variable} antialiased`}
      >
        {children}
        <footer className="fixed bottom-4 w-full flex justify-center">
          <Link href="https://github.com/Aburraq" target="_blank" rel="noopener noreferrer" className="text-white text-lg flex items-center gap-2 hover:scale-110 transition-transform">
            <span className="text-2xl walking">🐙</span>
            Aburraq on GitHub
            <span className="text-2xl walking">🐙</span>

          </Link>
        </footer>
      </body>
    </html>
  );
}

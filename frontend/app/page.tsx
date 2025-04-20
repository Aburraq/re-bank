'use client';
import { useState, useRef } from 'react';
import Link from 'next/link';
import Image from 'next/image';

export default function Home() {
  const [dropdownOpen, setDropdownOpen] = useState(false);
  const operationsRef = useRef<HTMLElement>(null);
  const handleDropdownToggle = () => {
    setDropdownOpen(prev => !prev);
    operationsRef.current?.scrollIntoView({ behavior: 'smooth' });
  };

  return (
    <main className="min-h-screen bg-[#111215] text-white flex flex-col justify-center items-center">
      {/* Navigation Bar */}
      <nav className="w-full flex justify-between items-center px-8 py-6 text-sm font-medium border-b border-white/10">
        <div className="text-2xl font-serif tracking-tight">Re-Bank</div>
        <div className="hidden md:flex gap-8">
          <Link href="features" className="hover:text-gray-400">Features</Link>
          <div className="relative">
            <button onClick={handleDropdownToggle} className="hover:text-gray-400 hover:rounded-lg focus:outline-none">Operations</button>
            {dropdownOpen && (
              <div className="absolute mt-2 bg-white text-black rounded-lg shadow-lg z-10">
                <Link href="/accounts" className="block px-4 py-2 hover:bg-gray-200 hover:rounded-lg">Accounts</Link>
                <Link href="/transactions" className="block px-4 py-2 hover:bg-gray-200 hover:rounded-lg">Transactions</Link>
                <Link href="/assets" className="block px-4 py-2 hover:bg-gray-200 hover:rounded-lg">Assets</Link>
                <Link href="/notifications" className="block px-4 py-2 hover:bg-gray-200 hover:rounded-lg">Notifications</Link>
              </div>
            )}
          </div>
          <Link href="help" className="hover:text-gray-400">Help</Link>
          <Link href="blog" className="hover:text-gray-400">Blog</Link>
        </div>
        <div className="flex gap-4">
          <Link href="/login" className="px-4 py-2 rounded-full bg-transparent border border-white hover:bg-white hover:text-black transition">Log In</Link>
          <Link href="/signup" className="px-4 py-2 rounded-full bg-white text-black font-semibold hover:bg-gray-200 transition">Open Account</Link>
        </div>
      </nav>
      {/* Hero Section */}
      <section className="flex flex-col-reverse md:flex-row w-full max-w-7xl items-center justify-between px-8 py-16 md:py-32 gap-12">
        {/* Left: Headline/Subheadline/CTA */}
        <div className="flex-1 flex flex-col items-start justify-center max-w-xl">
          <h1 className="text-4xl md:text-6xl font-serif font-semibold leading-tight mb-6">
            Experience <span className="text-white/80">Awesome Banking</span><br />
            Like Never Before
          </h1>
          <p className="text-lg md:text-xl text-white/70 mb-8 max-w-md">
            Real Bank but actually fake. Manage your spending, savings, investments, and overall financial life seamlessly in one convenient location.
          </p>
          <div className="flex gap-4 mb-10">
            <Link href="/signup" className="px-6 py-3 rounded-full bg-white text-black font-semibold text-lg shadow hover:bg-gray-200 transition">Open Account</Link>
            <Link href="/advice" className="px-6 py-3 rounded-full border border-white text-white font-semibold text-lg hover:bg-white hover:text-black transition">Get Advice</Link>
          </div>
          <div className="flex gap-8 text-sm text-white/60 mt-8">
            <div>
              <div className="text-xl font-bold text-white">175,923</div>
              <div>Satisfied customers</div>
            </div>
            <div>
              <div className="text-xl font-bold text-white">4.9</div>
              <div>App rating</div>
            </div>
            <div>
              <div className="text-xl font-bold text-white">200+</div>
              <div>Supported countries</div>
            </div>
          </div>
        </div>
        {/* Right: Phone Mockup */}
        <div className="flex-1 flex items-center justify-center">
          <Image
            src="/hero-mockup-dark.png"
            width={340}
            height={200}
            alt="App Mockup"
            className="w-[340px] md:w-[400px] rounded-2xl shadow-2xl border border-white/10 bg-black/20"
            style={{ objectFit: 'cover' }}
          />
        </div>
      </section>
     
    </main>
  );
}

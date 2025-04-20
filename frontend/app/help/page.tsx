'use client';
import Link from 'next/link';
import React from 'react';

export default function HelpPage() {
  return (
    <main className="min-h-screen bg-[#111215] text-white flex flex-col items-center justify-center space-y-6 p-8">
      <h1 className="text-6xl font-serif animate-bounce">Need Help?</h1>
      <p className="text-xl text-white/70 max-w-lg text-center">
        Our help section is still under construction. Meanwhile, enjoy this spinning money bag:
      </p>
      <div className="text-9xl animate-spin">💰</div>
      <p className="text-lg text-white/70">Just kidding! Drop us a line at <span className="text-red-500">support@re-bank.fake</span> (or don’t, we won’t judge).</p>
      <Link href="/" className="mt-8 text-red-500 hover:underline">Back to Home</Link>
    </main>
  );
}

'use client';
import Link from 'next/link';
import React from 'react';

export default function BlogPage() {
  return (
    <main className="min-h-screen bg-[#111215] text-white flex flex-col items-center justify-center space-y-6 p-8">
      <h1 className="text-6xl font-serif animate-pulse">Re-Bank Blog</h1>
      <p className="text-xl text-white/70 max-w-lg text-center">
        Stay tuned for groundbreaking articles like “Why Fake Banks Are the Future” and “10 Reasons to Love Zero Fees (Especially When They Don’t Exist)”.
      </p>
      <div className="grid grid-cols-1 md:grid-cols-2 gap-8 mt-8">
        <div className="p-6 bg-[#1e1e1e] rounded-xl shadow-lg hover:scale-105 transition">
          <h2 className="text-2xl font-semibold mb-2">Why Fake Banks Are the Future</h2>
          <p className="text-white/60">Spoiler: They can’t lend your money, so they can’t lose it!</p>
        </div>
        <div className="p-6 bg-[#1e1e1e] rounded-xl shadow-lg hover:scale-105 transition">
          <h2 className="text-2xl font-semibold mb-2">10 Reasons to Love Zero Fees</h2>
          <p className="text-white/60">Bonus reason: You’ll never have to pay them!</p>
        </div>
      </div>
      <Link href="/" className="mt-8 text-red-500 hover:underline">Back to Home</Link>
    </main>
  );
}

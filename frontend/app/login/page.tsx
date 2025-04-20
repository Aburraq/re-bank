'use client';
import React, { useState } from 'react';
import { useRouter } from 'next/navigation';

export default function LoginPage() {
  const [username, setUsername] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState('');
  const router = useRouter();

  const handleSubmit = async (e: React.FormEvent) => {
    e.preventDefault();
    setError('');
    const res = await fetch('/api/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ username, password }),
    });
    if (res.ok) {
      router.push('/');
    } else {
      const data = await res.json();
      setError(data.message || 'Login failed');
    }
  };

  return (
    <main className="min-h-screen bg-[#111215] text-white flex items-center justify-center">
      <form onSubmit={handleSubmit} className="bg-[#1e1e1e] p-8 rounded-xl shadow-lg w-full max-w-md">
        <h1 className="text-3xl font-serif mb-6 text-center">Log In</h1>
        {error && <p className="text-red-400 mb-4 text-center">{error}</p>}
        <label className="block mb-4">
          <span className="block mb-1">Username</span>
          <input
            type="text"
            value={username}
            onChange={e => setUsername(e.target.value)}
            required
            className="w-full p-2 rounded bg-[#272727] focus:outline-none focus:ring-2 focus:ring-red-500"
          />
        </label>
        <label className="block mb-6">
          <span className="block mb-1">Password</span>
          <input
            type="password"
            value={password}
            onChange={e => setPassword(e.target.value)}
            required
            className="w-full p-2 rounded bg-[#272727] focus:outline-none focus:ring-2 focus:ring-red-500"
          />
        </label>
        <button
          type="submit"
          className="w-full py-2 bg-red-600 hover:bg-red-500 text-white rounded-full font-semibold transition"
        >
          Log In
        </button>
      </form>
    </main>
  );
}

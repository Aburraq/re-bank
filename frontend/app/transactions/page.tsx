import Link from 'next/link';

export default async function TransactionsPage() {
  const res = await fetch('/api/transactions', { cache: 'no-store' });
  if (!res.ok) throw new Error('Failed to fetch transactions');
  const transactions = await res.json();
  return (
    <main className="min-h-screen p-8 bg-gray-50">
      <h1 className="text-2xl font-bold mb-4">Transactions</h1>
      <pre className="bg-white p-4 rounded shadow">{JSON.stringify(transactions, null, 2)}</pre>
      <Link href="/" className="mt-8 inline-block text-blue-500 hover:underline">
        Back to home
      </Link>
    </main>
  );
}

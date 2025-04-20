import Link from 'next/link';

export default function FeaturesPage() {
  return (
    <main className="min-h-screen bg-[#111215] text-white py-16">
      <div className="container mx-auto px-8">
        <h1 id="features" className="text-4xl font-serif font-semibold mb-8">Features</h1>
        <p className="text-lg text-white/70 mb-6">
          This application connects to several backend services. Here’s what you can do:
        </p>
        <ul className="space-y-4 list-disc list-inside text-lg text-white">
          <li>💰 Manage accounts via <code>/api/accounts</code> (create, view, deposit, withdraw)</li>
          <li>🔄 Process transactions via <code>/api/transactions</code> (new, list, details)</li>
          <li>📊 Track assets via <code>/api/assets</code> (list, details)</li>
          <li>🔔 Receive notifications via <code>/api/notifications</code> (list, by account)</li>
          <li>💡 Get playful advice via <code>/advice</code> (fun motto endpoint)</li>
        </ul>
        <div className="mt-10">
          <Link href="/" className="text-white underline hover:text-gray-400">Back to home</Link>
        </div>
      </div>
    </main>
  );
}
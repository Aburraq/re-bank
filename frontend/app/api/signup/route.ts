import { NextRequest, NextResponse } from 'next/server';

const BASE_URL = process.env.API_GATEWAY_URL || 'http://localhost:8080';

export async function POST(request: NextRequest) {
  const url = `${BASE_URL}/signup`;
  const res = await fetch(url, {
    method: 'POST',
    headers: request.headers,
    body: request.body,
    credentials: 'include',
  });
  const data = await res.json();
  const response = NextResponse.json(data, { status: res.status });
  // Forward Set-Cookie for session
  const setCookie = res.headers.get('set-cookie');
  if (setCookie) response.headers.set('set-cookie', setCookie);
  return response;
}

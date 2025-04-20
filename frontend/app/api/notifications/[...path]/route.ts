import { NextRequest, NextResponse } from 'next/server';

const BASE_URL = process.env.API_GATEWAY_URL || 'http://localhost:8080';

async function proxy(request: NextRequest, { params }: { params: { path?: string[] } }) {
  const path = params.path?.join('/');
  const url = path ? `${BASE_URL}/api/notifications/${path}` : `${BASE_URL}/api/notifications`;
  const res = await fetch(url, {
    method: request.method,
    headers: request.headers,
    body: request.body
  });
  const data = await res.json();
  return NextResponse.json(data, { status: res.status });
}

export async function GET(request: NextRequest, context: any) {
  return proxy(request, context);
}

export async function POST(request: NextRequest, context: any) {
  return proxy(request, context);
}

export async function PUT(request: NextRequest, context: any) {
  return proxy(request, context);
}

export async function PATCH(request: NextRequest, context: any) {
  return proxy(request, context);
}

export async function DELETE(request: NextRequest, context: any) {
  return proxy(request, context);
}

export async function OPTIONS(request: NextRequest, context: any) {
  return proxy(request, context);
}

'use server';

import {fetch} from "next/dist/compiled/@edge-runtime/primitives";
import {cookies} from "next/headers";
import {redirect} from "next/navigation";

export async function login(formData: FormData) {
    const username = String(formData.get('username') || "");
    const password = String(formData.get('password') || "");

    const res = await fetch(`${process.env.API_URL}/backoffice/login`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        body: JSON.stringify({
            username,
            password,
        }),
        cache: "no-cache",
    })

    if (!res.ok) {
        throw new Error("아이디 또는 비밀번호가 올바르지 않습니다.")
    }

    const data = await res.json();

    const cookieStore = await cookies();

    cookieStore.set("accessToken", data.accessToken, {
        httpOnly: true,
        secure: process.env.NODE_ENV === 'production',
        sameSite: 'lax',
        path: '/',
        maxAge: 60 * 60,
    });

    redirect('/backoffice');
}
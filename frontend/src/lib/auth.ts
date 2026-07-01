import 'server-only'

import {cache} from "react";
import {cookies} from "next/headers";
import {redirect} from "next/navigation";

export const requireUser = cache(async () => {
    const cookie = await cookies();
    const accessToken = cookie.get('accessToken')?.value;

    if (!accessToken) {
        redirect('/backoffice/login');
    }

    const res = await fetch(`${process.env.API_URL}/auth/me`, {
        headers: {
            Authorization: `Bearer ${accessToken}`,
        },
        cache: 'no-store',
    })

    if (res.status === 401) {
        redirect('/backoffice/login');
    }

    if (res.status === 403) {
        redirect('/403');
    }

    if (!res.ok) {
        throw new Error("로그인 확인 실패");
    }

    return await res.json() as Promise<{
        username:string,
        role:string,
    }>
})
'use server';

import {verifySession} from "@/src/lib";
import {redirect} from "next/navigation";

export async function createPost(title:string, content:string, categoryIds:number[]) {
    const accessToken =  await verifySession();

    const res = await fetch(`${process.env.API_URL}/backoffice/posts`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            Authorization: `Bearer ${accessToken}`,
        },
        cache: 'no-store',
        body: JSON.stringify({
            title,
            content,
            categories: categoryIds,
        }),
    })

    if (res.status === 401) {
        redirect('/backoffice/login');
    }

    if (res.status === 403) {
        redirect('/403');
    }

    if (!res.ok) {
        throw new Error('글 작성 실패')
    }

    redirect('/backoffice/posts');
}
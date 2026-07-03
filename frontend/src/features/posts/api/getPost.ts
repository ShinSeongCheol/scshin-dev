'use server';

import {Post} from "@/src/features/posts";
import {verifySession} from "@/src/lib";
import {redirect} from "next/navigation";

export async function getPost(id: number): Promise<Post> {
    const res = await fetch(`${process.env.API_URL}/posts/${id}`, {
        cache: 'no-store',
    })

    if (!res.ok) {
        throw new Error('게시글 조회 실패')
    }

    return res.json()
}

export async function getAdminPostById(postId: number): Promise<Post> {
    const accessToken = await verifySession();

    const res = await fetch(`${process.env.API_URL}/backoffice/posts/edit/${postId}`, {
        method: 'GET',
        headers: {
            'Authorization': `Bearer ${accessToken}`,
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
        throw new Error('글 조회 실패')
    }

    return await res.json()
}
import {Post} from "@/src/features/post/types";

export async function getPosts(): Promise<Post[]> {
    const res = await fetch(`${process.env.API_URL}/posts`, {
        cache: 'no-store',
    })

    if (!res.ok) {
        throw new Error('게시글 조회 실패')
    }

    return res.json()
}
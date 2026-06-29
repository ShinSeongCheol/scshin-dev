import {Post} from "@/src/features/post";

export async function getPost(id: number): Promise<Post> {
    const res = await fetch(`${process.env.API_URL}/posts/${id}`, {
        cache: 'no-store',
    })

    if (!res.ok) {
        throw new Error('게시글 조회 실패')
    }

    return res.json()
}
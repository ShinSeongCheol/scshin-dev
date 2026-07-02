import {Category} from "@/src/features/categories";
import {verifySession} from "@/src/lib";
import {redirect} from "next/navigation";

export async function getCategories(): Promise<Category[]> {
    const accessToken =  await verifySession();

    const res = await fetch(`${process.env.API_URL}/backoffice/categories`, {
        method: "GET",
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
        throw new Error('카테고리 목록 조회 실패')
    }

    return await res.json()
}
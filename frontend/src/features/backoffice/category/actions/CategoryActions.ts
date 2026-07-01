'use server'

import {verifySession} from "@/src/lib";
import {fetch} from "next/dist/compiled/@edge-runtime/primitives";
import {redirect} from "next/navigation";
import {revalidatePath} from "next/cache";

export async function createCategoryActions(formData: FormData) {
    const accessToken = await verifySession();

    const parentCategoryId = formData.get('parentId');
    const categoryName = formData.get('name');
    const description = formData.get('description');
    const slug = formData.get('slug');
    const useYn = formData.get('use_yn') || 'N';

    const res = await fetch(`${process.env.API_URL}/backoffice/categories`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `Bearer ${accessToken}`,
        },
        body: JSON.stringify({
            parentCategoryId,
            categoryName,
            slug,
            description,
            useYn
        }),
    })

    if (res.status === 401) {
        redirect('/backoffice/login')
    }

    if (res.status === 403) {
        redirect('/403')
    }

    if (!res.ok) {
        throw new Error(`카테고리 등록 실패.`);
    }

    revalidatePath('/backoffice/categories');
}

export async function updateCategoryActions(formData: FormData) {
    const accessToken = await verifySession();
    const categoryId = formData.get('categoryId');
    const parentCategoryId = formData.get('parentId');
    const categoryName = formData.get('name');
    const description = formData.get('description');
    const slug = formData.get('slug');
    const useYn = formData.get('use_yn') || 'N';

    const res = await fetch(`${process.env.API_URL}/backoffice/categories`, {
        method: "PATCH",
        headers: {
            "Content-Type": "application/json",
            "Accept": "application/json",
            "Authorization": `Bearer ${accessToken}`,
        },
        body: JSON.stringify({
            categoryId,
            parentCategoryId,
            categoryName,
            slug,
            description,
            useYn
        }),
    })

    if (res.status === 401) {
        redirect('/backoffice/login')
    }

    if (res.status === 403) {
        redirect('/403')
    }

    if (!res.ok) {
        throw new Error(`카테고리 수정 실패.`);
    }

    revalidatePath('/backoffice/categories');
}
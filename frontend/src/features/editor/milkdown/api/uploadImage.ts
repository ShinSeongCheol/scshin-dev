'use server';
import {verifySession} from "@/src/lib";

export async function uploadImage(formData: FormData): Promise<string> {
    const accessToken = await verifySession();

    const res = await fetch(`${process.env.API_URL}/image/upload`, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Authorization': `Bearer ${accessToken}`,
        },
        body: formData,
    });

    if (!res.ok) {
        throw new Error('이미지 업로드 실패');
    }


    const data = await res.json();

    return `${process.env.API_URL}${data.filePath}`;
}
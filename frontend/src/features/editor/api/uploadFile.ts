export async function uploadFile(file: File): Promise<{
    url: string;
    size: number;
    title: string;
}> {
    const formData = new FormData();
    formData.append('file', file);

    const res = await fetch('/api/uploads/file', {
        method: 'POST',
        body: formData,
    });

    if (!res.ok) {
        throw new Error('파일 업로드 실패');
    }

    return await res.json();
}
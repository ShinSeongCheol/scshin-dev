export type Post = {
    id: number;
    title: string;
    content: string;
    createdAt: Date;
    thumbnailUrl: string;
}

export type PostDetail = {
    id: number;
    title: string;
    content: string;
    authorId: number;
    createdAt: Date;
    updatedAt: Date;
    views:number;
    categoryIds: number[];
}
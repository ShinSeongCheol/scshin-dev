'use server';

import {createPost, updatePost} from "@/src/features/posts/api";

export async function createPostAction(title:string, content:string, categories:number[]) {
    await createPost(title, content, categories);
}

export async function updatePostAction(postId:number, title:string, content:string, categories:number[]) {
    await updatePost(postId, title, content, categories);
}
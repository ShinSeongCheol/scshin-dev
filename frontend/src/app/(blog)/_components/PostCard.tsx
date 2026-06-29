'use client';

import Image from "next/image";
import {Post} from "@/src/features/post/types";

export default function PostCard({id, title, content, createdAt, thumbnailUrl}: Post) {

    const imageUrl = process.env.NEXT_PUBLIC_IMAGE_BASE_URL + thumbnailUrl;

    return (
        <article className="group flex flex-col bg-white rounded-2xl overflow-hidden border border-gray-200 transition-all duration-300 cursor-pointer hover:transform hover:-translate-y-1 hover:shadow-2xl hover:border-slate-400">
            <div className="relative h-48 overflow-hidden">
                <Image
                    src={thumbnailUrl ? imageUrl : '/default_no_image.png'}
                    alt={title}
                    width={640}
                    height={640}
                    className="w-full h-full object-cover transition-transform duration-500 group-hover:scale-105"
                    />
                <span className="absolute top-4 left-4 px-3 py-1.5 rounded-lg text-xs font-semibold uppercase tracking-[0.5px] text-white bg-blue-400">카테고리</span>
            </div>
            <div className="flex flex-col flex-1 h-full p-4">
                <h2
                    className="text-xl text-gray-600 font-semibold leading-snug line-clamp-2 overflow-hidden transition-colors duration-300">
                    {title}
                </h2>
                <p className="flex-1 mt-2 text-sm text-gray-400 line-clamp-3 overflow-hidden transition-colors duration-300">
                    {content}
                </p>
                <div className="mt-2 flex items-center justify-end text-xs text-slate-600 transition-colors duration-300">
                    <span>{new Date(createdAt).toLocaleDateString(undefined, {
                        year: 'numeric',
                        month: 'long',
                        day: 'numeric',
                    })}</span>
                </div>
            </div>
        </article>
    )
}
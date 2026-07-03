'use client';

import {MilkdownEditor} from "@/src/features/editor/milkdown/ui";
import {ChangeEvent, useEffect, useState} from "react";
import {createPost} from "@/src/features/posts/api";
import Link from "next/link";


export default function NewPostPage() {

    const [title, setTitle] = useState("");
    const [content, setContent] = useState('');
    const [categories, setCategories] = useState<number[]>([]);

    const handleTitleChange = (event: ChangeEvent<HTMLInputElement, HTMLInputElement>) => {
        setTitle(event.currentTarget.value);
    }

    const handleContentChange = (markdown:string) => {
        setContent(markdown);
    }

    const handleSave = async () => {
        await createPost(title, content, categories);
    };

    return (
        <div className="min-h-0 flex-1 overflow-hidden flex h-full flex-col gap-4 p-6">
            <div className="flex min-h-0 flex-1 flex-col gap-4">
                <div className={'flex-1 flex flex-col min-h-0'}>
                    {/* 제목 입력 */}
                    <div className="px-30 py-4 border-b border-gray-200 pb-8">
                        <input
                            type="text"
                            placeholder="제목을 입력하세요."
                            className="w-full border-none bg-transparent text-3xl font-extrabold text-slate-900 outline-none placeholder:text-slate-300"
                            value={title}
                            onChange={handleTitleChange}
                        />
                    </div>

                    {/* 에디터 영역 */}
                    <div className={'flex-1 h-full overflow-y-auto'}>
                        <MilkdownEditor initialMarkdown={''} handleChange={handleContentChange}/>
                    </div>
                </div>

                {/* 하단 영역 */}
                <div className="flex items-center justify-end border-t border-gray-200 pt-8">
                    <div className="flex gap-2">
                        <Link
                            className="flex items-center h-10 rounded-xl border border-slate-300 bg-white px-5 text-sm font-semibold text-slate-700 shadow-sm transition hover:bg-slate-100 hover:cursor-pointer"
                            href={"/backoffice/posts"}
                        >
                            취소
                        </Link>
                        <button
                            type="button"
                            className="h-10 rounded-xl bg-blue-600 px-5 text-sm font-semibold text-white shadow-sm transition hover:bg-blue-700 hover:cursor-pointer"
                            onClick={handleSave}
                        >
                            저장
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
}
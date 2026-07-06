'use client';

import React, {ChangeEvent, Dispatch, ReactNode, SetStateAction, useState} from "react";
import {MilkdownEditor} from "@/src/features/editor/milkdown/ui";
import Link from "next/link";
import {CategoryTreeSelect} from "@/src/features/categories/ui/CategoryTreeSelect";
import {Category} from "@/src/features/categories";

interface Props {
    initialTitle:string,
    initialContent:string,
    initialCategories: Category[],
    initialCategoryIds: number[],
    action: (title:string, content:string, categories:number[]) => Promise<void>,
}

export default function PostForm({initialTitle, initialContent, initialCategories, initialCategoryIds, action}: Props) {

    const [title, setTitle] = useState(initialTitle);
    const [content, setContent] = useState(initialContent);
    const [categories, setCategories] = useState<Category[]>(initialCategories);
    const [categoryIds, setCategoryIds] = useState<number[]>(initialCategoryIds);

    const handleTitleChange = (event: ChangeEvent<HTMLInputElement, HTMLInputElement>) => {
        setTitle(event.currentTarget.value);
    }

    const handleContentChange = (markdown:string) => {
        setContent(markdown);
    }

    const handleAction = () => {
        void action(title, content, categoryIds);
    }

    return (
        <div className="min-h-0 flex-1 overflow-hidden flex h-full flex-col gap-4 p-6">

            <div className="flex min-h-0 flex-1 flex-col gap-4">
                <div className={'flex-1 flex flex-col gap-4 min-h-0'}>
                    {/* 제목 입력 */}
                    <div className="p-4 border-b border-gray-200 pb-8">
                        <input
                            type="text"
                            placeholder="제목을 입력하세요."
                            className="w-full border-none bg-transparent text-3xl font-extrabold text-slate-900 outline-none placeholder:text-slate-300"
                            value={title}
                            onChange={handleTitleChange}
                        />
                    </div>

                    <div>
                        <CategoryTreeSelect
                            categories={categories}
                            selectedIds={categoryIds}
                            onChange={setCategoryIds}
                        />
                    </div>

                    {/* 에디터 영역 */}
                    <div className={'flex-1 h-full overflow-y-auto border border-gray-200'}>
                        <MilkdownEditor initialMarkdown={initialContent} handleChange={handleContentChange}/>
                    </div>
                </div>

                {/* 하단 영역 */}
                <div className="flex items-center justify-end pt-8">
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
                            onClick={handleAction}
                        >
                            저장
                        </button>
                    </div>
                </div>
            </div>
        </div>
    )
}
'use client';

import CategoryList from "@/src/features/categories/ui/CategoryList";
import {createCategoryActions} from "@/src/features/categories/actions";
import {Category} from "@/src/features/categories";
import {useState} from "react";
import CategoryForm from "@/src/features/categories/ui/CategoryForm";
import {updateCategoryActions} from "@/src/features/categories/actions/CategoryActions";

interface Props {
    categories: Category[];
}

export default function CategoryDashboard({categories}: Props) {

    const [selectedCategory, setSelectedCategory] = useState<Category | null>(null)

    const handleUpdateAction = async (formData: FormData) => {
        await updateCategoryActions(formData);
        setSelectedCategory(null);
    }

    return (
        <div className="flex flex-col gap-6 p-6">
            {/* 상단 타이틀 */}
            <div className="flex items-center justify-between border-b border-gray-100 pb-4">
                <div>
                    <h1 className="text-2xl font-bold text-gray-900">카테고리 관리</h1>
                    <p className="text-sm text-gray-500 mt-1">블로그의 메뉴 구조와 카테고리를 관리합니다.</p>
                </div>

                <button
                    className="px-4 py-2 bg-violet-500 hover:bg-violet-600 text-white font-medium rounded-xl shadow-sm transition-colors hover:cursor-pointer"
                    onClick={() => setSelectedCategory(null)}
                >
                    + 새 카테고리 추가
                </button>
            </div>

            <div className="flex gap-6 ">
                <CategoryList selectedCategory={selectedCategory} categories={categories} onSelect={setSelectedCategory}></CategoryList>
                <CategoryForm
                    key={selectedCategory?.id || "create"}
                    selectedCategory={selectedCategory}
                    categories={categories}
                    action={selectedCategory ?  handleUpdateAction : createCategoryActions}
                    onClear={() => setSelectedCategory(null)}
                />
            </div>
        </div>
    )
}
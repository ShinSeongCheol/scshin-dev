import {Category} from "@/src/features/backoffice/category";
import CategoryListItem from "@/src/features/backoffice/category/ui/CategoryListItem";

interface Props {
    selectedCategory: Category | null;
    categories: Category[];
    onSelect: (selected: Category) => void;
}

export default function CategoryList({selectedCategory, categories, onSelect}: Props) {
    return (
        <div className="w-full bg-white border border-gray-200 rounded-xl shadow-sm overflow-hidden">
            <table className="w-full text-left border-collapse">
                <thead>
                <tr className="bg-gray-50 text-gray-600 text-sm font-medium border-b border-gray-100">
                    <th className="p-4 w-16 text-center">번호</th>
                    <th className="p-4">카테고리(Slug)</th>
                    <th className="p-4 w-32">등록일</th>
                    <th className="p-4 w-32 text-center">상태</th>
                </tr>
                </thead>
                <tbody className="divide-y divide-gray-50 text-sm text-gray-700">
                {categories.map((category) => {
                    return <CategoryListItem key={category.id} selectedCategory={selectedCategory} category={category} onSelect={onSelect} depth={category.depth}/>
                })}
                </tbody>
            </table>
        </div>
    )
}
import {Category} from "@/src/features/backoffice/category";

interface Props {
    categories: Category[];
    onSelect: (selected: Category) => void;
}

export default function CategoryList({categories, onSelect}: Props) {
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
                    {categories.map((category) => (
                        <tr className="hover:bg-gray-50/50 transition-colors" key={category.id}
                            onClick={() => onSelect(category)}>
                            <td className="p-4 text-center text-gray-400">{category.id}</td>
                            <td className="p-4 font-medium text-violet-600">{category.categoryName} ({category.slug})</td>
                            <td className="p-4 text-gray-500">
                                {new Date(category.createdAt).toLocaleDateString(undefined, {
                                    year: 'numeric',
                                    month: 'long',
                                    day: 'numeric',
                                })}
                            </td>
                            <td className="p-4 text-center">
                            <span
                                className={`px-2.5 py-1 text-xs font-semibold rounded-full ${
                                    category.useYn === 'Y'
                                        ? "bg-green-50 text-green-700"  // 🟢 활성화 (초록색 배지)
                                        : "bg-red-100 text-red-700"   // ⚪ 비활성화 (회색 배지)
                                }`}
                            >
                                {category.useYn === 'Y' ? "활성화" : "비활성화"}
                            </span>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    )
}
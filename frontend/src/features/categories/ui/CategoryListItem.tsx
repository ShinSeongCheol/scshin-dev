import { Category } from "@/src/features/categories";

interface Props {
    selectedCategory: Category | null;
    category: Category;
    onSelect: (category: Category) => void;
    depth?: number;
}

export default function CategoryListItem({ selectedCategory, category, onSelect, depth = 0 }: Props) {
    return (
        <>
            <tr
                className={`transition-colors cursor-pointer ${
                    selectedCategory?.id === category.id
                        ? 'bg-violet-50/70 hover:bg-violet-50'
                        : 'hover:bg-gray-50/80 bg-white'
                }`}
                onClick={() => onSelect(category)}
            >
                <td className="p-4 text-center text-gray-400">{category.id}</td>

                <td className="p-4 font-medium text-violet-600" style={{ paddingLeft: `${(depth - 1) * 24 + 16}px` }}>
                    {depth > 1 && <span className="text-gray-300 mr-1">↳</span>}
                    {category.categoryName} ({category.slug})
                </td>

                <td className="p-4 text-gray-500">
                    {category.createdAt
                        ? new Date(category.createdAt).toLocaleDateString(undefined, {
                            year: 'numeric',
                            month: 'long',
                            day: 'numeric',
                        })
                        : '-'
                    }
                </td>
                <td className="p-4 text-center">
                    <span
                        className={`px-2.5 py-1 text-xs font-semibold rounded-full ${
                            category.useYn === 'Y'
                                ? "bg-green-50 text-green-700"
                                : "bg-red-100 text-red-700"
                        }`}
                    >
                        {category.useYn === 'Y' ? "활성화" : "비활성화"}
                    </span>
                </td>
            </tr>

            {category.childrenList && category.childrenList.map((child) => (
                <CategoryListItem
                    key={child.id}
                    selectedCategory={selectedCategory}
                    category={child}
                    onSelect={onSelect}
                    depth={child.depth}
                />
            ))}
        </>
    );
}
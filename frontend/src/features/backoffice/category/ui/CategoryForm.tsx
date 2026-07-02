import {Category} from "@/src/features/backoffice/category";

interface Props {
    categories: Category[];
    selectedCategory: Category | null;
    action: (formData: FormData) => Promise<void>;
    onClear: () => void;
}

function flattenCategories(categories: Category[]): Omit<Category, 'childrenList'>[] {
    return categories.flatMap((category) => {
        const {childrenList, ...currentCategory} = category;

        const flatChildren = childrenList && childrenList.length > 0
            ? flattenCategories(childrenList)
            : [];

        return [currentCategory, ...flatChildren];
    });
}

export default function CategoryForm({selectedCategory, categories, action, onClear}: Props) {
    const flattedCategories = flattenCategories(categories)
    return (
        <div className="w-full bg-white rounded-xl shadow-sm overflow-hidden">
            <form className="flex flex-col gap-6 rounded-2xl border border-gray-200 bg-white p-6 shadow-sm" action={action}>
                <div className={'flex flex-col gap-1'}>
                    <h2 className="text-lg font-semibold text-gray-900">카테고리 정보</h2>
                    <p className="text-sm text-gray-500">
                        블로그에서 사용할 카테고리 정보를 입력하세요.
                    </p>
                </div>

                {selectedCategory && (
                    <input type="hidden" name="categoryId" value={selectedCategory.id} />
                )}

                <div className="flex flex-col gap-5">
                    <div>
                        <label
                            htmlFor="categoryName"
                            className="mb-2 block text-sm font-medium text-gray-700"
                        >
                            카테고리명 <span className="text-red-500">*</span>
                        </label>
                        <input
                            id="categoryName"
                            name="name"
                            type="text"
                            defaultValue={selectedCategory?.categoryName || ""}
                            required
                            placeholder="예: Spring Boot"
                            className="w-full rounded-xl border border-gray-300 bg-white px-4 py-3 text-sm text-gray-900 outline-none transition placeholder:text-gray-400 hover:border-gray-400 focus:border-blue-500 focus:ring-4 focus:ring-blue-100"
                        />
                    </div>

                    <div>
                        <label
                            htmlFor="categorySlug"
                            className="mb-2 block text-sm font-medium text-gray-700"
                        >
                            Slug <span className="text-red-500">*</span>
                        </label>
                        <input
                            id="categorySlug"
                            name="slug"
                            type="text"
                            defaultValue={selectedCategory?.slug || ""}
                            required
                            placeholder="예: spring-boot"
                            className="w-full rounded-xl border border-gray-300 bg-white px-4 py-3 text-sm text-gray-900 outline-none transition placeholder:text-gray-400 hover:border-gray-400 focus:border-blue-500 focus:ring-4 focus:ring-blue-100"
                        />
                        <p className="mt-1 text-xs text-gray-400">
                            URL에 사용됩니다. 영문 소문자, 숫자, 하이픈을 권장합니다.
                        </p>
                    </div>

                    <div>
                        <label
                            htmlFor="categoryDescription"
                            className="mb-2 block text-sm font-medium text-gray-700"
                        >
                            설명
                        </label>
                        <textarea
                            id="categoryDescription"
                            name="description"
                            rows={4}
                            placeholder="카테고리에 대한 설명을 입력하세요."
                            defaultValue={selectedCategory?.description || ""}
                            className="w-full resize-none rounded-xl border border-gray-300 bg-white px-4 py-3 text-sm text-gray-900 outline-none transition placeholder:text-gray-400 hover:border-gray-400 focus:border-blue-500 focus:ring-4 focus:ring-blue-100"
                        />
                    </div>

                    <div>
                        <label
                            htmlFor="parentCategory"
                            className="mb-2 block text-sm font-medium text-gray-700"
                        >
                            부모 카테고리
                        </label>
                        <select
                            id="parentCategory"
                            name="parentId"
                            defaultValue={selectedCategory?.parentCategoryId || ""}
                            className="w-full rounded-xl border border-gray-300 bg-white px-4 py-3 text-sm text-gray-900 outline-none transition hover:border-gray-400 focus:border-blue-500 focus:ring-4 focus:ring-blue-100"
                        >
                            <option value="">부모 카테고리 없음</option>
                            {flattedCategories.map((category) => (
                                <option key={category.id} value={category.id}>{category.categoryName}</option>
                            ))}
                        </select>
                    </div>

                    <div className="flex items-center justify-between rounded-xl border border-gray-200 bg-gray-50 px-4 py-4">
                        <div>
                            <span className="block text-sm font-medium text-gray-700">
                              카테고리 사용
                            </span>
                            <span className="mt-1 block text-xs text-gray-400">
                              비활성화하면 블로그 화면에 노출되지 않습니다.
                            </span>
                        </div>

                        <label className="relative inline-flex cursor-pointer items-center">
                            <input
                                type="checkbox"
                                name="use_yn"
                                value="Y"
                                className="peer sr-only"
                                defaultChecked={selectedCategory ? selectedCategory.useYn === 'Y' : true}
                            />

                            <div className="h-6 w-11 rounded-full bg-gray-300 transition peer-checked:bg-blue-500 peer-focus:ring-4 peer-focus:ring-blue-100 after:absolute after:left-0.5 after:top-0.5 after:h-5 after:w-5 after:rounded-full after:border after:border-gray-300 after:bg-white after:transition-all after:content-[''] peer-checked:after:translate-x-full peer-checked:after:border-white" />
                        </label>
                    </div>
                </div>

                <div className="flex items-center justify-end gap-3 border-t border-gray-100 pt-5">
                    {selectedCategory && (
                        <button type="button" onClick={onClear} className="px-4 py-2 bg-gray-100 text-gray-600 font-medium rounded-xl hover:bg-gray-200">
                            취소
                        </button>
                    )}

                    <button
                        className="rounded-xl bg-blue-500 px-5 py-2.5 text-sm font-semibold text-white shadow-sm transition hover:bg-blue-600 focus:scale-95 focus:outline-none focus:ring-4 focus:ring-blue-100 hover:cursor-pointer"
                        type="submit"
                    >
                        {selectedCategory ? "수정" : "저장"}
                    </button>
                </div>
            </form>
        </div>
    )
}
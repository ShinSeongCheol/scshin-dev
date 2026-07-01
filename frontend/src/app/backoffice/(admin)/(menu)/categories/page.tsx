import {getCategories} from "@/src/features/backoffice/category/api";
import {createCategoryActions} from "@/src/features/backoffice/category/actions";

export default async function CategoryPage() {

    const categories = await getCategories();

    return(
        <div className="flex flex-col gap-6 p-6">
            {/* 상단 타이틀 */}
            <div className="flex items-center justify-between border-b border-gray-100 pb-4">
                <div>
                    <h1 className="text-2xl font-bold text-gray-900">카테고리 관리</h1>
                    <p className="text-sm text-gray-500 mt-1">블로그의 메뉴 구조와 카테고리를 관리합니다.</p>
                </div>

                <button className="px-4 py-2 bg-violet-500 hover:bg-violet-600 text-white font-medium rounded-xl shadow-sm transition-colors hover:cursor-pointer">
                    + 새 카테고리 추가
                </button>
            </div>

            <div className="flex gap-6 ">

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
                            <tr className="hover:bg-gray-50/50 transition-colors" key={category.id}>
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

                <div className="w-full bg-white rounded-xl shadow-sm overflow-hidden">
                    <form className="flex flex-col gap-6 rounded-2xl border border-gray-200 bg-white p-6 shadow-sm" action={createCategoryActions}>
                        <div className={'flex flex-col gap-1'}>
                            <h2 className="text-lg font-semibold text-gray-900">카테고리 정보</h2>
                            <p className="text-sm text-gray-500">
                                블로그에서 사용할 카테고리 정보를 입력하세요.
                            </p>
                        </div>

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
                                    defaultValue=""
                                    className="w-full rounded-xl border border-gray-300 bg-white px-4 py-3 text-sm text-gray-900 outline-none transition hover:border-gray-400 focus:border-blue-500 focus:ring-4 focus:ring-blue-100"
                                >
                                    <option value="">부모 카테고리 없음</option>
                                    {categories.map((category) => (
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
                                        defaultChecked
                                    />

                                    <div className="h-6 w-11 rounded-full bg-gray-300 transition peer-checked:bg-blue-500 peer-focus:ring-4 peer-focus:ring-blue-100 after:absolute after:left-0.5 after:top-0.5 after:h-5 after:w-5 after:rounded-full after:border after:border-gray-300 after:bg-white after:transition-all after:content-[''] peer-checked:after:translate-x-full peer-checked:after:border-white" />
                                </label>
                            </div>
                        </div>

                        <div className="flex items-center justify-end gap-3 border-t border-gray-100 pt-5">
                            <button
                                id="cancel-button"
                                className="rounded-xl border border-gray-300 bg-white px-5 py-2.5 text-sm font-medium text-gray-700 transition hover:bg-gray-50 focus:scale-95 focus:outline-none hover:cursor-pointer"
                                type="button"
                            >
                                취소
                            </button>

                            <button
                                className="rounded-xl bg-blue-500 px-5 py-2.5 text-sm font-semibold text-white shadow-sm transition hover:bg-blue-600 focus:scale-95 focus:outline-none focus:ring-4 focus:ring-blue-100 hover:cursor-pointer"
                                type="submit"
                            >
                                저장
                            </button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    )
}
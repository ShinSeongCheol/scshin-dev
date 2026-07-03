import Link from "next/link";
import {PostList} from "@/src/features/posts/ui";
import {getPosts} from "@/src/features/posts/api";

export default async function PostPage() {

    const posts = await getPosts();

    return(
        <div className="flex flex-col gap-4 p-6">

            {/* 상단 타이틀 & 버튼 섹션 */}
            <div className="flex justify-between items-center border-b border-gray-100 pb-4">
                <div>
                    <h1 className="text-2xl font-bold text-gray-900">게시글 관리</h1>
                    <p className="text-sm text-gray-500 mt-1">총 128개의 글이 있습니다.</p>
                </div>
                <Link
                    href={"/backoffice/posts/new"}
                    className="px-4 py-2 bg-violet-500 hover:bg-violet-600 text-white font-medium rounded-xl shadow-sm transition-colors cursor-pointer"
                >
                    + 새 글 작성
                </Link>
            </div>

            {/* 검색 및 필터 컨트롤 바 섹션 */}
            <div className="flex flex-col sm:flex-row gap-4 justify-between items-center bg-gray-50 p-4 rounded-xl">
                <div className="flex gap-2 w-full sm:w-auto">
                    <select className="px-3 py-2 bg-white border border-gray-200 rounded-lg text-sm text-gray-600 focus:outline-none focus:border-violet-500">
                        <option>전체 카테고리</option>
                    </select>
                    <select className="px-3 py-2 bg-white border border-gray-200 rounded-lg text-sm text-gray-600 focus:outline-none focus:border-violet-500">
                        <option>전체 상태</option>
                    </select>
                </div>
                <div className="w-full sm:w-80">
                    <input
                        type="text"
                        placeholder="글 제목으로 검색..."
                        className="w-full px-4 py-2 bg-white border border-gray-200 rounded-lg text-sm focus:outline-none focus:border-violet-500"
                    />
                </div>
            </div>

            {/*  메인 데이터 테이블 섹션 */}
            <PostList posts={posts}/>

        </div>
    )
}
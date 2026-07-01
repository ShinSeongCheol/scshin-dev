export default  function PostPage() {
    return(
        <div className="flex flex-col gap-4 p-6">

            {/* 상단 타이틀 & 버튼 섹션 */}
            <div className="flex justify-between items-center border-b border-gray-100 pb-4">
                <div>
                    <h1 className="text-2xl font-bold text-gray-900">게시글 관리</h1>
                    <p className="text-sm text-gray-500 mt-1">총 128개의 글이 있습니다.</p>
                </div>
                <button className="px-4 py-2 bg-violet-500 hover:bg-violet-600 text-white font-medium rounded-xl shadow-sm transition-colors">
                    + 새 글 작성
                </button>
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
            <div className="bg-white border border-gray-100 rounded-xl shadow-sm overflow-hidden">
                <table className="w-full text-left border-collapse">
                    <thead>
                    <tr className="bg-gray-50 text-gray-600 text-sm font-medium border-b border-gray-100">
                        <th className="p-4 w-16 text-center">번호</th>
                        <th className="p-4 w-32">카테고리</th>
                        <th className="p-4">제목</th>
                        <th className="p-4 w-32">등록일</th>
                        <th className="p-4 w-24 text-center">상태</th>
                    </tr>
                    </thead>
                    <tbody className="divide-y divide-gray-50 text-sm text-gray-700">
                    {/* 행(Row) 반복 구간 예시 */}
                    <tr className="hover:bg-gray-50/50 transition-colors">
                        <td className="p-4 text-center text-gray-400">12</td>
                        <td className="p-4 font-medium text-violet-600">Next.js</td>
                        <td className="p-4 font-medium text-gray-900 hover:underline cursor-pointer">Next.js 서버 컴포넌트와 클라이언트 컴포넌트의 차이점</td>
                        <td className="p-4 text-gray-500">2026-07-01</td>
                        <td className="p-4 text-center">
                            <span className="px-2.5 py-1 text-xs font-semibold bg-green-50 text-green-700 rounded-full">공개</span>
                        </td>
                    </tr>
                    </tbody>
                </table>
            </div>

        </div>
    )
}
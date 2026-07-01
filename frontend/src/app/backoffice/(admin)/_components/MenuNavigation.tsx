import Link from "next/link";
import {logout} from "@/src/features/backoffice/actions";

export default async function MenuNavigation() {
    return (
        <nav className={'w-64 bg-white border border-gray-200 flex flex-col'}>
            {/*로고*/}
            <div className="p-6 border-b border-gray-200 flex items-center justify-between">
                <Link
                    href={"/"}
                    className="flex items-center gap-2 text-lg font-bold"
                >
                    <span className="logo-icon">✨</span>
                    <span
                        className="logo-text gradient-text bg-linear-to-br from-violet-500 to-fuchsia-500 bg-clip-text text-transparent">&lt;scshin /&gt;</span>
                </Link>
                <span
                    className="admin-badge px-2 py-1 bg-linear-to-br from-violet-500 to-fuchsia-500 text-white text-xs font-bold rounded-lg tracking-[0.5]">
                    ADMIN
                </span>
            </div>

            {/*메뉴*/}
            <div className="flex-1 p-4 flex flex-col gap-1">
                {/*<a*/}
                {/*   th:class="|flex items-center gap-3 px-4 py-3 rounded-xl font-medium transition-all duration-150 text-left w-full*/}
                {/*             ${menu == 'dashboard' ? 'bg-backoffice-active text-backoffice-accent-primary cursor-pointer' : 'text-backoffice-text-secondary  hover:bg-backoffice-tertiary hover:text-backoffice-text-primary cursor-pointer'}|"*/}
                {/*   >*/}
                {/*    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">*/}
                {/*        <rect x="3" y="3" width="7" height="9"></rect>*/}
                {/*        <rect x="14" y="3" width="7" height="5"></rect>*/}
                {/*        <rect x="14" y="12" width="7" height="9"></rect>*/}
                {/*        <rect x="3" y="16" width="7" height="5"></rect>*/}
                {/*    </svg>*/}
                {/*    대시보드*/}
                {/*</a>*/}
                <Link href={"/backoffice/categories"}
                      className="flex items-center gap-4 p-4 rounded-xl font-medium text-left text-gray-500 hover:bg-violet-500/50 hover:text-white cursor-pointer"
                    // th:class="|flex items-center gap-3 px-4 py-3 rounded-xl font-medium transition-all duration-150 text-left w-full
                   //           ${menu == 'category' ? 'bg-backoffice-active text-backoffice-accent-primary cursor-pointer' : 'text-backoffice-text-secondary  hover:bg-backoffice-tertiary hover:text-backoffice-text-primary cursor-pointer'}|"
                   >
                    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                        <path d="M22 19a2 2 0 0 1-2 2H4a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h5l2 3h9a2 2 0 0 1 2 2z"></path>
                    </svg>
                    <span>
                        카테고리 관리
                    </span>
                </Link>
                <Link href={"/backoffice/posts"}
                      className="flex items-center gap-4 p-4 rounded-xl font-medium text-left text-gray-500 hover:bg-violet-500/50 hover:text-white cursor-pointer"
                   // th:class="|flex items-center gap-3 px-4 py-3 rounded-xl font-medium transition-all duration-150 text-left w-full
                   //           ${menu == 'post' ? 'bg-backoffice-active text-backoffice-accent-primary cursor-pointer' : 'text-backoffice-text-secondary  hover:bg-backoffice-tertiary hover:text-backoffice-text-primary cursor-pointer'}|"
                   >
                    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor" strokeWidth="2">
                        <path d="M14 2H6a2 2 0 0 0-2 2v16a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V8z"></path>
                        <polyline points="14 2 14 8 20 8"></polyline>
                        <line x1="16" y1="13" x2="8" y2="13"></line>
                        <line x1="16" y1="17" x2="8" y2="17"></line>
                    </svg>
                    <span>
                        글 관리
                    </span>
                </Link>
            </div>

            {/*하단*/}
            <div className="p-4 border-t border-gray-200">
                <button
                        className="flex items-center gap-4 p-4 rounded-xl font-semibold text-gray-500 transition-all duration-150 w-full hover:bg-[rgba(83,195,243,0.1)] hover:text-[#28b4f0] cursor-pointer">
                    <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" viewBox="0 0 24 24" fill="none"
                         stroke="currentColor" strokeWidth="2" strokeLinecap="round" strokeLinejoin="round">
                        <path d="M19 21v-2a4 4 0 0 0-4-4H9a4 4 0 0 0-4 4v2"/>
                        <circle cx="12" cy="7" r="4"/>
                    </svg>
                    <span>
                        내 정보
                    </span>
                </button>
            </div>

            {/*로그 아웃 버튼*/}
            <div className="p-4 border-t border-gray-200">
                <button type="button"
                        className="flex items-center gap-4 p-4 rounded-xl font-semibold text-gray-500 transition-all duration-150 w-full hover:bg-[rgba(239,68,68,0.1)] hover:text-[#ef4444] cursor-pointer"
                        onClick={logout}
                >
                    <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                         strokeWidth="2">
                        <path d="M9 21H5a2 2 0 0 1-2-2V5a2 2 0 0 1 2-2h4M16 17l5-5-5-5M21 12H9"></path>
                    </svg>
                    <span>
                        로그아웃
                    </span>
                </button>
            </div>
        </nav>
    )
}
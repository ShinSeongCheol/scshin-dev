import {Metadata} from "next";
import Link from "next/link";
import {login} from "@/src/features/backoffice/actions";

export async function generateMetadata(): Promise<Metadata> {
    return {
        title: '로그인',
        description: '로그인',
    }
}

export default function LoginPage() {
    return (
        <div className="flex items-center justify-center">
            <div
                className={"w-full max-w-md bg-white rounded-3xl px-10 py-12 shadow-2xl border border-gray-200 text-center"}>
                <div className="mb-8">
                    <div className="inline-flex items-center gap-2 text-2xl font-bold mb-6">
                        <span className="text-3xl">🔐</span>
                        <span
                            className="text-3xl gradient-text bg-linear-to-br from-violet-500 to-fuchsia-500 bg-clip-text text-transparent">Admin</span>
                    </div>
                    <h1 className="text-2xl mb-2">관리자 로그인</h1>
                    <p className="text-slate-500 text-sm">블로그를 관리하려면 아이디와 비밀번호를 입력하세요</p>
                </div>

                <form className="text-left" action={login}>
                    <div className="form-group mb-6">
                        <label htmlFor="username"
                               className="block mb-2 font-semibold text-sm text-slate-500">아이디</label>
                        <input type="text" id="username" name="username" placeholder="아이디를 입력하세요" required
                               autoComplete="current-password"
                               className="w-full px-4.5 py-3.5 border-2 border-gray-300 rounded-xl transition-all duration-150 resize-y focus:outline-none focus:border-violet-500 focus:shadow-xl hover:shadow-xl placeholder:text-slate-500"/>
                        <label htmlFor="adminPassword"
                               className="block mt-4 mb-2 font-semibold text-sm text-secondary">비밀번호</label>
                        <input type="password" name="password" id="adminPassword" placeholder="비밀번호를 입력하세요"
                               required
                               autoComplete="current-password"
                               className="w-full px-4.5 py-3.5 border-2 border-gray-300 rounded-xl transition-all duration-150 resize-y focus:outline-none focus:border-violet-500 focus:shadow-xl hover:shadow-xl placeholder:text-slate-500"/>
                    </div>

                    <button type="submit"
                            className="w-full flex items-center justify-center gap-2 px-7 py-3.5 rounded-xl font-semibold text-sm text-white bg-linear-to-br from-violet-500 to-fuchsia-500 transition-all duration-150 shadow-backoffice hover:transform hover:-transform-y-2 hover:cursor-pointer">
                        <svg width="20" height="20" viewBox="0 0 24 24" fill="none" stroke="currentColor"
                             strokeWidth="2">
                            <path d="M15 3h4a2 2 0 0 1 2 2v14a2 2 0 0 1-2 2h-4M10 17l5-5-5-5M13.8 12H3"></path>
                        </svg>
                        로그인
                    </button>
                </form>

                <Link
                    href={"/"}
                    className="back-link inline-block mt-6 text-backoffice-text-tertiary text-sm transition-colors duration-150 hover:text-backoffice-accent-primary"
                >
                    ← 블로그로 돌아가기
                </Link>
            </div>
        </div>
    )
}
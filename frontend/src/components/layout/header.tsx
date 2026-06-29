import Link from "next/link";

export default function Header() {
    return (
        <header className={'sticky top-0 flex justify-center bg-white backdrop-blur-2xl border-b border-gray-200 z-50'}>
            <div className={'flex items-center justify-between max-w-7xl w-full p-4'}>
                <Link href={'/'} className="flex items-center gap-2 text-xl font-bold">
                    <span
                        className="bg-linear-to-br from-violet-500 to-fuchsia-500 bg-clip-text text-transparent">&lt;scshin /&gt;</span>
                </Link>
                <nav className="hidden md:flex md:gap-2">
                    {["전체"].map((item) => (
                        <Link href="#"
                              key={item}
                              className="px-4 py-2 rounded-xl font-medium transition-all duration-150 text-slate-500 hover:bg-slate-100"
                              >
                            {item}
                        </Link>
                    ))}
                </nav>
                <div className="flex items-center gap-2">
                    <button
                        className="size-10 rounded-xl flex items-center justify-center transition-all duration-150 cursor-pointer hover:bg-slate-100"
                        id="themeToggle" aria-label="테마 변경">
                        <svg className="block dark:hidden" width="20" height="20" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" strokeWidth="2">
                            <circle cx="12" cy="12" r="5"></circle>
                            <path
                                d="M12 1v2M12 21v2M4.22 4.22l1.42 1.42M18.36 18.36l1.42 1.42M1 12h2M21 12h2M4.22 19.78l1.42-1.42M18.36 5.64l1.42-1.42"></path>
                        </svg>
                        <svg className="hidden dark:block" width="20" height="20" viewBox="0 0 24 24" fill="none"
                             stroke="currentColor" strokeWidth="2">
                            <path d="M21 12.79A9 9 0 1 1 11.21 3 7 7 0 0 0 21 12.79z"></path>
                        </svg>
                    </button>
                </div>
            </div>
        </header>
    )
}
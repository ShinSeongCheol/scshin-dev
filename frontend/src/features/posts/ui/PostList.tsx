import {Post} from '@/src/features/posts'
import Link from "next/link";

interface Props {
    posts: Post[],
}

export default function PostList({posts}: Props){

    return (
        <div className="w-full bg-white border border-gray-200 rounded-xl shadow-sm overflow-hidden">
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
                {posts.map((post, index) => (
                    <tr className="hover:bg-gray-50/50 transition-colors" key={post.id}>
                        <td className="p-4 text-center text-gray-400">{index + 1}</td>
                        <td className="p-4 font-medium text-violet-600">카테고리</td>
                        <td className="font-medium text-gray-900">
                            <Link href={`/backoffice/posts/edit/${post.id}`} className="block p-4 hover:underline  cursor-pointer">
                                {post.title}
                            </Link>
                        </td>
                        <td className="p-4 text-gray-500">{new Date(post.createdAt).toLocaleString(undefined, {
                            year: 'numeric',
                            month: 'long',
                            day: 'numeric',
                        })}</td>
                        <td className="p-4 text-center">
                            <span className="px-2.5 py-1 text-xs font-semibold bg-green-50 text-green-700 rounded-full">상태</span>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    )
}
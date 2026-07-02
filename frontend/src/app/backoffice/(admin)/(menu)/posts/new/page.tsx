'use client';

import dynamic from "next/dynamic";
import {useEffect, useRef, useState} from "react";
import {EditorClientRef} from "@/src/features/editor/ui/EditorClient";
import {OutputData} from "@editorjs/editorjs";

const EditorClient = dynamic(() => import('@/src/features/editor/ui/EditorClient'), {
    ssr: false,
    loading: () => <div>에디터 로딩중...</div>
});

export default function NewPostPage() {

    const [data, setData] = useState<OutputData>();

    const editorRef = useRef<EditorClientRef>(null);

    const handleSave = async () => {
        editorRef.current?.save().then((outputData) => {
            setData(outputData);
            console.log("저장된 에디터 데이터:", outputData);

            // 여기서 추가로 백엔드 DB에 글 저장하는 API를 호출하시면 됩니다.
        }).catch((error) => {
            console.error("데이터 추출 실패:", error);
        });
    };

    useEffect(() => {
        console.log(data)
    }, [data]);

    return (
        <div className="min-h-0 flex-1 overflow-hidden flex h-full flex-col gap-4 p-6">
            <div className="flex min-h-0 flex-1 flex-col gap-4">
                {/* 상단 영역 */}
                <div className="shrink-0 flex items-center justify-between border-b border-gray-100 pb-4">
                    <div>
                        <h1 className="text-2xl font-bold text-gray-900">게시글 작성</h1>
                    </div>

                    <div className="flex gap-2">
                        <button
                            type="button"
                            className="h-10 rounded-xl border border-slate-300 bg-white px-5 text-sm font-semibold text-slate-700 shadow-sm transition hover:bg-slate-100"
                        >
                            취소
                        </button>
                        <button
                            type="button"
                            className="h-10 rounded-xl bg-blue-600 px-5 text-sm font-semibold text-white shadow-sm transition hover:bg-blue-700"
                            onClick={handleSave}
                        >
                            저장
                        </button>
                    </div>
                </div>

                {/* 제목 입력 */}
                <div className="shrink-0 rounded-2xl border border-gray-200 bg-white px-7 py-6 shadow-sm">
                    <input
                        type="text"
                        placeholder="제목을 입력하세요."
                        className="w-full border-none bg-transparent text-2xl font-extrabold text-slate-900 outline-none placeholder:text-slate-300"
                    />
                </div>

                {/* 에디터 영역 */}
                <div
                    className="min-h-0 flex-1 overflow-y-auto rounded-2xl border border-gray-200 bg-white px-12 py-10 shadow-sm"
                >
                    <EditorClient ref={editorRef}/>
                </div>
            </div>
        </div>
    );
}
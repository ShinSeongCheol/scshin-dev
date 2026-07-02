'use client';

import {forwardRef, useEffect, useImperativeHandle, useRef} from 'react';
import type EditorJSType from '@editorjs/editorjs';
import type { OutputData } from '@editorjs/editorjs';
import type { ToolConstructable } from '@editorjs/editorjs';
import {uploadFile, uploadImage} from "@/src/features/editor/api";

export interface EditorClientRef {
    save: () => Promise<OutputData>;
}

const EditorClient = forwardRef<EditorClientRef, {}>((props, ref) => {
    const holderRef = useRef<HTMLDivElement | null>(null);
    const editorRef = useRef<EditorJSType | null>(null);
    const initializingRef = useRef(false);

    useEffect(() => {
        let canceled = false;

        const initEditor = async () => {
            if (initializingRef.current) return;
            if (editorRef.current) return;
            if (!holderRef.current) return;

            initializingRef.current = true;
            holderRef.current.innerHTML = '';

            const [
                { default: EditorJS },
                { default: Header },
                { default: ImageTool },
                { default: AttachesTool },
                { default: List },
                { default: Checklist },
                { default: Quote },
                { default: Warning },
                { default: Marker },
                { default: CodeTool },
                { default: Delimiter },
                { default: InlineCode },
                { default: LinkTool },
                { default: Embed },
                { default: Table },
            ] = await Promise.all([
                import('@editorjs/editorjs'),
                import('@editorjs/header'),
                import('@editorjs/image'),
                import('@editorjs/attaches'),
                import('@editorjs/list'),
                import('@editorjs/checklist'),
                import('@editorjs/quote'),
                import('@editorjs/warning'),
                import('@editorjs/marker'),
                import('@editorjs/code'),
                import('@editorjs/delimiter'),
                import('@editorjs/inline-code'),
                import('@editorjs/link'),
                import('@editorjs/embed'),
                import('@editorjs/table'),
            ]);

            if (canceled || !holderRef.current) {
                initializingRef.current = false;
                return;
            }

            const editor = new EditorJS({
                holder: holderRef.current,
                placeholder: '내용을 입력하세요...',
                tools: {
                    header: {
                        class: Header,
                        inlineToolbar: ['marker', 'link'],
                        config: {
                            placeholder: '제목을 입력하세요',
                            levels: [2, 3, 4],
                            defaultLevel: 2,
                        },
                        shortcut: 'CMD+SHIFT+H',
                    },

                    image: {
                        class: ImageTool,
                        config: {
                            uploader: {
                                uploadByFile(file: File) {
                                    const formData = new FormData();
                                    formData.append('image', file);

                                    return uploadImage(formData).then((resultUrl) => {
                                        return {
                                            success: 1,
                                            file: {
                                                url: resultUrl,
                                            },
                                        };
                                    });
                                },
                            },
                        },
                    },

                    attaches: {
                        class: AttachesTool,
                        config: {
                            uploader: {
                                uploadByFile(file: File) {
                                    return uploadFile(file).then((resultData) => {
                                        return {
                                            success: 1,
                                            file: {
                                                url: resultData.url,
                                                size: resultData.size,
                                                name: resultData.title,
                                                title: resultData.title,
                                            },
                                        };
                                    });
                                },
                            },
                        },
                    },

                    list: {
                        class: List,
                        inlineToolbar: true,
                        shortcut: 'CMD+SHIFT+L',
                    },

                    checklist: {
                        class: Checklist,
                        inlineToolbar: true,
                    },

                    quote: {
                        class: Quote,
                        inlineToolbar: true,
                        config: {
                            quotePlaceholder: '인용문을 입력하세요',
                            captionPlaceholder: '출처 또는 작성자',
                        },
                        shortcut: 'CMD+SHIFT+O',
                    },

                    warning: {
                        class: Warning,
                    },

                    marker: {
                        class: Marker,
                        shortcut: 'CMD+SHIFT+M',
                    },

                    code: {
                        class: CodeTool,
                        shortcut: 'CMD+SHIFT+C',
                    },

                    delimiter: {
                        class: Delimiter,
                    },

                    inlineCode: {
                        class: InlineCode,
                        shortcut: 'CMD+SHIFT+I',
                    },

                    linkTool: {
                        class: LinkTool,
                        config: {
                            endpoint: '/api/editor/link',
                        },
                    },

                    embed: {
                        class: Embed,
                    },

                    table: {
                        class: Table as unknown as ToolConstructable,
                        inlineToolbar: true,
                        shortcut: 'CMD+ALT+T',
                    },
                },

                data: {
                    blocks: [
                        {
                            type: 'paragraph',
                            data: {
                                text: '',
                            },
                        },
                    ],
                },

                onReady: () => {
                    console.log('Editor.js ready');
                },

                onChange: async (api, event) => {
                    console.log('something changed', event);
                },
            });

            try {
                await editor.isReady;

                if (canceled) {
                    editor.destroy?.();
                    return;
                }

                editorRef.current = editor;
            } catch (error) {
                console.error('EditorJS init error:', error);
            } finally {
                initializingRef.current = false;
            }
        };

        void initEditor();

        return () => {
            canceled = true;

            const editor = editorRef.current;

            if (editor && typeof editor.destroy === 'function') {
                editor.destroy();
            }

            editorRef.current = null;
            initializingRef.current = false;

            if (holderRef.current) {
                holderRef.current.innerHTML = '';
            }
        };
    }, []);

    useImperativeHandle(ref, () => ({
        async save() {
            if (!editorRef.current) {
                throw new Error("에디터가 아직 초기화되지 않았습니다.");
            }
            return await editorRef.current.save();
        }
    }));

    return (
        <div ref={holderRef} className={''}/>
    );
});

EditorClient.displayName = "EditorClient";
export default EditorClient;
'use client';

import {useEffect, useMemo, useRef, useState} from "react";
import {Category} from "@/src/features/categories";

type CategoryTreeSelectProps = {
    categories: Category[];
    selectedIds: number[];
    onChange: (categoryIds: number[]) => void;
    placeholder?: string;
};

type CategorySearchItem = {
    id: number;
    label: string;
};

export function CategoryTreeSelect({categories, selectedIds, onChange, placeholder = "카테고리 선택",}: CategoryTreeSelectProps) {
    const rootRef = useRef<HTMLDivElement>(null);

    const [open, setOpen] = useState(false);
    const [keyword, setKeyword] = useState("");

    useEffect(() => {
        function handleClickOutside(event: MouseEvent) {
            if (!rootRef.current) return;

            if (!rootRef.current.contains(event.target as Node)) {
                setOpen(false);
            }
        }

        function handleKeyDown(event: KeyboardEvent) {
            if (event.key === "Escape") {
                setOpen(false);
            }
        }

        document.addEventListener("mousedown", handleClickOutside);
        document.addEventListener("keydown", handleKeyDown);

        return () => {
            document.removeEventListener("mousedown", handleClickOutside);
            document.removeEventListener("keydown", handleKeyDown);
        };
    }, []);

    const selectedItems = useMemo(() => {
        return selectedIds
            .map((id) => {
                const path = findCategoryPath(categories, id);

                if (!path) return null;

                return {
                    id,
                    label: path.map((category) => category.categoryName).join(" > "),
                };
            })
            .filter(Boolean) as CategorySearchItem[];
    }, [categories, selectedIds]);

    const leafItems = useMemo(() => {
        return flattenLeafCategories(categories);
    }, [categories]);

    const searchItems = useMemo(() => {
        const normalizedKeyword = normalize(keyword);

        if (!normalizedKeyword) {
            return leafItems;
        }

        return leafItems.filter((item) =>
            normalize(item.label).includes(normalizedKeyword)
        );
    }, [leafItems, keyword]);

    const triggerText =
        selectedItems.length === 0
            ? placeholder
            : selectedItems.length === 1
                ? selectedItems[0].label
                : `${selectedItems.length}개 카테고리 선택됨`;

    function toggleCategory(categoryId: number) {
        const selected = selectedIds.includes(categoryId);

        if (selected) {
            onChange(selectedIds.filter((id) => id !== categoryId));
            return;
        }

        onChange([...selectedIds, categoryId]);
    }

    function removeCategory(categoryId: number) {
        onChange(selectedIds.filter((id) => id !== categoryId));
    }

    return (
        <div ref={rootRef} className="relative w-full">
            <button
                type="button"
                onClick={() => setOpen((prev) => !prev)}
                className="flex items-center justify-between rounded-lg border border-gray-300 bg-white px-3 py-2 text-left text-sm shadow-sm hover:bg-gray-50"
            >
        <span
            className={
                selectedItems.length > 0
                    ? "truncate text-gray-900"
                    : "truncate text-gray-400"
            }
        >
          {triggerText}
        </span>

                <span className="ml-2 text-gray-400">{open ? "▴" : "▾"}</span>
            </button>

            {open && (
                <div
                    className="absolute  z-50 mt-2 rounded-xl border border-gray-200 bg-white p-3 shadow-lg">
                    <input
                        value={keyword}
                        onChange={(e) => setKeyword(e.target.value)}
                        placeholder="카테고리 검색"
                        className="mb-3 w-full rounded-lg border border-gray-300 px-3 py-2 text-sm outline-none focus:border-gray-900"
                    />

                    <div className="max-h-72 overflow-y-auto pr-1">
                        {keyword.trim() ? (
                            <div className="space-y-1">
                                {searchItems.length > 0 ? (
                                    searchItems.map((item) => {
                                        const selected = selectedIds.includes(item.id);

                                        return (
                                            <button
                                                key={item.id}
                                                type="button"
                                                onClick={() => toggleCategory(item.id)}
                                                className={[
                                                    "flex w-full items-center gap-2 rounded-md px-2 py-2 text-left text-sm hover:bg-gray-100",
                                                    selected ? "bg-gray-100 font-medium" : "",
                                                ].join(" ")}
                                            >
                                                <CheckboxIcon checked={selected}/>
                                                <span className="truncate">{item.label}</span>
                                            </button>
                                        );
                                    })
                                ) : (
                                    <p className="px-2 py-6 text-center text-sm text-gray-400">
                                        검색 결과가 없습니다.
                                    </p>
                                )}
                            </div>
                        ) : (
                            <div className="space-y-1">
                                {categories.map((category) => (
                                    <CategoryTreeItem
                                        key={category.id}
                                        category={category}
                                        selectedIds={selectedIds}
                                        onToggle={toggleCategory}
                                    />
                                ))}
                            </div>
                        )}
                    </div>
                </div>
            )}

            {selectedItems.length > 0 && (
                <div className="mt-2 flex flex-wrap gap-2">
                    {selectedItems.map((item) => (
                        <span
                            key={item.id}
                            className="inline-flex max-w-full items-center gap-1 rounded-full bg-gray-100 px-2.5 py-1 text-xs text-gray-700"
                        >
              <span className="truncate">{item.label}</span>

              <button
                  type="button"
                  onClick={() => removeCategory(item.id)}
                  className="ml-1 rounded-full px-1 text-gray-400 hover:bg-gray-200 hover:text-gray-700"
                  aria-label={`${item.label} 제거`}
              >
                ×
              </button>
            </span>
                    ))}
                </div>
            )}
        </div>
    );
}

function CategoryTreeItem({category, selectedIds, onToggle,}: { category: Category; selectedIds: number[]; onToggle: (categoryId: number) => void; }) {
    const [expanded, setExpanded] = useState(true);

    const children = category.childrenList ?? [];
    const hasChildren = children.length > 0;
    const selected = selectedIds.includes(category.id);

    if (hasChildren) {
        return (
            <div>
                <button
                    type="button"
                    onClick={() => setExpanded((prev) => !prev)}
                    className="flex w-full items-center gap-2 rounded-md px-2 py-1.5 text-left text-sm font-medium text-gray-800 hover:bg-gray-100"
                >
          <span className="w-4 text-gray-400">
            {expanded ? "▾" : "▸"}
          </span>

                    <span>{category.categoryName}</span>

                    <span className="ml-auto text-xs font-normal text-gray-400">
            그룹
          </span>
                </button>

                {expanded && (
                    <div className="ml-4 border-l border-gray-100 pl-2">
                        {children.map((child) => (
                            <CategoryTreeItem
                                key={child.id}
                                category={child}
                                selectedIds={selectedIds}
                                onToggle={onToggle}
                            />
                        ))}
                    </div>
                )}
            </div>
        );
    }

    return (
        <button
            type="button"
            onClick={() => onToggle(category.id)}
            className={[
                "flex w-full items-center gap-2 rounded-md px-2 py-1.5 text-left text-sm hover:bg-gray-100",
                selected ? "bg-gray-100 font-medium text-gray-900" : "text-gray-700",
            ].join(" ")}
        >
            <span className="w-4"/>
            <CheckboxIcon checked={selected}/>
            <span>{category.categoryName}</span>
        </button>
    );
}

function CheckboxIcon({checked}: { checked: boolean }) {
    return (
        <span
            className={[
                "flex h-4 w-4 items-center justify-center rounded border text-[10px]",
                checked
                    ? "border-gray-900 bg-gray-900 text-white"
                    : "border-gray-300 bg-white text-transparent",
            ].join(" ")}
        >
      ✓
    </span>
    );
}

function findCategoryPath(
    categories: Category[],
    categoryId: number,
    path: Category[] = []
): Category[] | null {
    for (const category of categories) {
        const currentPath = [...path, category];

        if (category.id === categoryId) {
            return currentPath;
        }

        const found = findCategoryPath(
            category.childrenList ?? [],
            categoryId,
            currentPath
        );

        if (found) {
            return found;
        }
    }

    return null;
}

function flattenLeafCategories(
    categories: Category[],
    path: Category[] = []
): CategorySearchItem[] {
    return categories.flatMap((category) => {
        const currentPath = [...path, category];
        const children = category.childrenList ?? [];

        if (children.length === 0) {
            return [
                {
                    id: category.id,
                    label: currentPath.map((item) => item.categoryName).join(" > "),
                },
            ];
        }

        return flattenLeafCategories(children, currentPath);
    });
}

function normalize(value: string) {
    return value.trim().toLowerCase();
}
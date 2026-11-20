#!/usr/bin/env python3
"""Copy problem-meta.json into index.html as `const META = {...};`.

problem-meta.json is the source of truth for each problem's one-line
statement and difficulty (keyed by LeetCode slug). The site is a single
static file, so the data is embedded rather than fetched at runtime.

Usage:  python3 scripts/embed_meta.py
"""
import json
import pathlib

root = pathlib.Path(__file__).resolve().parent.parent
meta = json.loads((root / 'problem-meta.json').read_text())
html_path = root / 'index.html'
lines = html_path.read_text().split('\n')

embedded = 'const META = ' + json.dumps(meta, ensure_ascii=False, separators=(',', ':')) + ';'
existing = [i for i, l in enumerate(lines) if l.startswith('const META = ')]
if existing:
    lines[existing[0]] = embedded
else:
    anchor = next(i for i, l in enumerate(lines) if l.startswith('const PROBLEMS = '))
    lines.insert(anchor + 1, embedded)

html_path.write_text('\n'.join(lines))
print(f'Embedded {len(meta)} problems into {html_path.name}')
